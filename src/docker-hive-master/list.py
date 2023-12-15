from hdfs import InsecureClient

# HDFS Configuration
hdfs_url = 'http://localhost:50070'
hdfs_path = '/user/hive/warehouse/sensor'
file_name = 'alldata.csv'  # Adjust the filename as per your requirement

client = InsecureClient(hdfs_url, user='root')

def read_file_contents_from_hdfs(hdfs_path, file_name):
    try:
        # Construct the full path to the file
        hdfs_file_path = f'{hdfs_path}/{file_name}'

        # Read the contents of the file
        with client.read(hdfs_file_path, encoding='utf-8') as reader:
            file_contents = reader.read()

        return file_contents
    except Exception as e:
        print(f"Error: {e}")
        return None

if __name__ == '__main__':
    # Read contents of the file in the sensor HDFS folder
    contents = read_file_contents_from_hdfs(hdfs_path, file_name)

    if contents is not None:
        print(f"Contents of the file '{file_name}':")
        print(contents)
    else:
        print(f"Unable to read contents of the file '{file_name}'.")
