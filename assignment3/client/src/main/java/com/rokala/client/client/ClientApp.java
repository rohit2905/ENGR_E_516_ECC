package com.rokala.client.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApp implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(ClientApp.class, args);
	}

	@GetMapping("/")
	public String hello() throws IOException {
		return "If the server is running and if you are seeing this, then a file from server has been transferred to you. Please verify by checking the clientdata directory in this client filesystem!!";
	}

	@Override
	public void run(String... args) throws Exception {
	    String serverUrl = ("http://server:8085");
	    // Get the project directory dynamically
	    String projectDir = System.getProperty("user.dir");

	    // Append the "data" directory to the project directory
	    String clientDataDir = projectDir + "/clientdata";

	    String fileName = clientDataDir + "/receivedFile.txt";

	    try {
			// Create directory if it does not exist
			Files.createDirectories(Paths.get(clientDataDir));

			// Create file if it does not exist
			if (!Files.exists(Paths.get(fileName))) {
				Files.createFile(Paths.get(fileName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	    // Retrieve file and checksum from server
	    ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(serverUrl + "/getFile", byte[].class);
	    byte[] fileBytes = responseEntity.getBody();
	    HttpHeaders headers = responseEntity.getHeaders();
	    String checksum = headers.getFirst("Checksum");
	    
	    // Verify checksum
	    if (verifyChecksum(fileName, checksum)) {
	        System.out.println("Checksum Verified!");
	    } else {
	        System.out.println("Checksum verification failed.");
	    }
	    // Save file to client data directory
	    try (FileOutputStream fos = new FileOutputStream(fileName)) {
	        fos.write(fileBytes);
	    }

	    System.out.println("File received and saved: " + fileName);

	    
	}

	private boolean verifyChecksum(String fileName, String expectedChecksum) throws NoSuchAlgorithmException, IOException {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] fileBytes = Files.readAllBytes(Paths.get(fileName));
	    byte[] hashBytes = digest.digest(fileBytes);
	    String actualChecksum = bytesToHex(hashBytes);
	    return actualChecksum.equals(expectedChecksum);
	}

	private String bytesToHex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte aByte : bytes) {
	        sb.append(String.format("%02x", aByte));
	    }
	    return sb.toString();
	}

}
