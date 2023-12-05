from kafka import KafkaConsumer
from hdfs import InsecureClient
import json
import csv

# Kafka Consumer Configuration
bootstrap_servers = 'localhost:9092'
topic = 'sensor'
group_id = 'my_consumer_group'

# HDFS Configuration
hdfs_url = 'http://localhost:50070'
hdfs_path = '/user/hive/csv'

client = InsecureClient(hdfs_url, user='root')


def consume_from_kafka_and_write_to_hdfs():
    consumer = KafkaConsumer(topic, group_id=group_id, bootstrap_servers=bootstrap_servers, auto_offset_reset='earliest')

    try:
        # List to store messages
        all_messages = []

        while True:
            msg = consumer.poll(timeout_ms=1000)  # Poll for messages with a timeout

            if not msg:
                break  # No more messages, exit the loop

            for _, messages in msg.items():
                for message in messages:
                    data = json.loads(message.value.decode('utf-8'))

                    print(f"Processing message: {data}")

                    sensor_id = data['sensor_id']
                    value = data['value']
                    timestamp = data['timestamp']

                    # Append the data to the list
                    all_messages.append(data)

        # Write all messages to a CSV file in HDFS
        hdfs_file_path = f'{hdfs_path}/all_data.csv'
        print(f"Writing to HDFS file: {hdfs_file_path}")

        with client.write(hdfs_file_path, overwrite=True) as writer:
            csv_writer = csv.DictWriter(writer, fieldnames=data.keys())
            
            # Write CSV header
            csv_writer.writeheader()

            # Write all messages to CSV
            csv_writer.writerows(all_messages)

    except Exception as e:
        print(f"Error: {e}")
    finally:
        consumer.close()

if __name__ == '__main__':
    consume_from_kafka_and_write_to_hdfs()
