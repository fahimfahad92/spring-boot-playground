package com.example.java21sb3.sqs;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.java21sb3.ContainerInitializer;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

/**
 * Publishes to a LocalStack FIFO queue. A consumer test (SQS → MessageListener → MySQL) used to
 * live here too; it returns when MessageListener and EventQueuesProperties are re-enabled in
 * src/main.
 */
public class SqsMessageTest extends ContainerInitializer {

  @Test
  void isLocalstackRunning() {
    assertTrue(localStack.isRunning());
  }

  @Test
  void shouldPublishMessageToSqs() {
    Message message = new Message(UUID.randomUUID(), "Hello World");

    var request =
        SendMessageRequest.builder()
            .messageBody(message.toString())
            .messageGroupId(UUID.randomUUID().toString())
            .messageDeduplicationId(UUID.randomUUID().toString())
            .queueUrl(queueUrl)
            .build();

    sqsClient.sendMessage(request);

    await()
        .atMost(3, SECONDS)
        .untilAsserted(
            () -> {
              loadAttributes();
              assertThat(numberOfMessagesInQueue()).isEqualTo(1);
              assertThat(numberOfMessagesNotVisibleInQueue()).isEqualTo(0);
            });
  }
}
