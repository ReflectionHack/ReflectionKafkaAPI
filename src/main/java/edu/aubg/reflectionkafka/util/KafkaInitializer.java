package edu.aubg.reflectionkafka.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;

import java.util.Properties;

public class KafkaInitializer implements SmartLifecycle {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    private boolean running = false;

    @Override
    public void start() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        try (AdminClient adminClient = AdminClient.create(properties)) {
            adminClient.describeCluster();
            running = true;
        } catch (Exception e) {
            System.out.println("Kafka is not available");
        }
    }

    @Override
    public void stop() {}

    @Override
    public boolean isRunning() {
        return running;
    }
}
