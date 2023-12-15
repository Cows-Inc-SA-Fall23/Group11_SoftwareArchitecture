package madasi.sensorManager.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.PrivilegedExceptionAction;

@Service
public class HdfsService {

    private final Configuration hadoopConfig;
    

	Logger logger = LoggerFactory.getLogger(HdfsService.class);

    public HdfsService(Configuration hadoopConfig) {
        this.hadoopConfig = hadoopConfig;
    }

    public <T> T readJsonFromHdfs(String hdfsPath, TypeReference<T> valueType) throws IOException {
        Path path = new Path(hdfsPath);
        try (FileSystem fs = FileSystem.get(hadoopConfig);
             FSDataInputStream inputStream = fs.open(path)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, valueType);
        }
    }
    
    public void writeCsvToHdfs(String hdfsPath, String jsonData) throws IOException {
        Path path = new Path(hdfsPath);
        ObjectMapper mapper = new ObjectMapper();
        byte[] jsonBytes = mapper.writeValueAsBytes(jsonData);
        FileSystem fs = FileSystem.get(hadoopConfig);
        FSDataOutputStream outputStream = fs.create(path, true);
        outputStream.write(jsonBytes);
    }
    
	/*
	 * public void writeFileToHDFS(String pathString, String data) throws
	 * IOException { Path path = new Path(pathString); // ObjectMapper mapper = new
	 * ObjectMapper(); // byte[] jsonBytes = mapper.writeValueAsBytes(data);
	 * FileSystem fs = FileSystem.get(hadoopConfig); FSDataOutputStream outputStream
	 * = fs.create(path, true); BufferedWriter bufferedWriter = new
	 * BufferedWriter(new OutputStreamWriter(outputStream,StandardCharsets.UTF_8));
	 * bufferedWriter.write(data); bufferedWriter.close(); fs.close(); }
	 */
    
    public void writeFileToHDFS(String pathString, String data) throws IOException {
        Path path = new Path(pathString);
        FileSystem fs = FileSystem.get(hadoopConfig);

        // Set permission to 777 for both files and directories
        FsPermission permission = new FsPermission((short) 0777);
//        Path path2 = new Path("/user/hive/warehouse/");
//        fs.delete(path2, true);
        // Check if the directory exists, if not create it with 777 permissions
        Path parentDir = path.getParent();
        if (!fs.exists(parentDir)) {
            fs.mkdirs(parentDir, permission);
        }

        // Create the file with 777 permissions
        FSDataOutputStream outputStream = fs.create(path, permission, true, 
                                                    fs.getConf().getInt("io.file.buffer.size", 4096), 
                                                    fs.getDefaultReplication(path), 
                                                    fs.getDefaultBlockSize(path), 
                                                    null);
        

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();
        fs.close();
    }
}
