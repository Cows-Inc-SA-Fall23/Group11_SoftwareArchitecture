from hdfs import InsecureClient
import json

def read_json_from_hdfs(hdfs_url, hdfs_path):
    # Create an HDFS client
    client = InsecureClient(hdfs_url, user='root')

    # Read the JSON file from HDFS
    with client.read(hdfs_path, encoding='utf-8') as file:
        # Parse JSON data
        json_data = json.load(file)

    return json_data

if __name__ == "__main__":
    # Set the HDFS URL and path to the JSON file
    hdfs_url = "http://localhost:50070"  # Replace with your HDFS Namenode URL
    hdfs_path = "/user/hive/csv/data_100.json"  # Replace with your HDFS path

    try:
        # Read JSON data from HDFS
        json_data = read_json_from_hdfs(hdfs_url, hdfs_path)

        # Display the JSON data
        print(json_data)
    except Exception as e:
        print(f"Error: {e}")
