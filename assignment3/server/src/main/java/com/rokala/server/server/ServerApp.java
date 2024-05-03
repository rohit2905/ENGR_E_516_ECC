package com.rokala.server.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerApp implements CommandLineRunner {

	private final ResourceLoader resourceLoader;

	public ServerApp(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApp.class, args);
	}

	@GetMapping("/")
	public String hello() throws IOException {
		return "Hello, This is Server running! A File has been generated and stored in server, Please use /getFile to download it here";
	}

	@GetMapping("/getFile")
	public ResponseEntity<String> getTransferFileContent() throws IOException, NoSuchAlgorithmException {
		String projectDir = System.getProperty("user.dir");

		// Append the "data" directory to the project directory
		String serverDataDir = projectDir + "/serverdata";
		String fileName = serverDataDir + "/transferFile.txt";
		Path filePath = Paths.get(fileName);
		String content = new String(Files.readAllBytes(filePath));

		// Calculate checksum
		String checksum = calculateChecksum(fileName);

		// Set checksum in response headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Checksum", checksum);

		return ResponseEntity.ok().headers(headers).body(content);
	}

	@Override
	public void run(String... args) throws Exception {
		int port = Integer.parseInt("8085");
		// Get the project directory dynamically
		String projectDir = System.getProperty("user.dir");

		// Append the "data" directory to the project directory
		String serverDataDir = projectDir + "/serverdata";
		String fileName = serverDataDir + "/transferFile.txt";
		int fileSize = 1024; // 1KB
		String randomText = generateRandomText(fileSize);

		// Create directory if it does not exist
		Files.createDirectories(Paths.get(serverDataDir));

		// Create file if it does not exist and write random text to it
		if (!Files.exists(Paths.get(fileName))) {
			Files.write(Paths.get(fileName), randomText.getBytes(StandardCharsets.US_ASCII), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);

		}
		System.out.println(Paths.get(fileName));

		// Calculate checksum
		String checksum = calculateChecksum(fileName);

		System.out.println("Server started on port " + port);
		System.out.println("File with random text generated and saved: " + fileName);
		System.out.println("Checksum: " + checksum);
		System.out.println("File size Generated: " + fileSize + " bytes");
	}

	private String generateRandomText(int size) {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder sb = new StringBuilder(size);
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String randomText = sb.toString();
		if (randomText.length() < size) {
			randomText = String.format("%1$-" + size + "s", randomText); // Pad the string to ensure it is exactly 1024
																			// characters
		}
		randomText = randomText.toString().replace("\r\n", "\n").replace("\r", "\n");
		return "***ECC-ASSIGNMENT-3-rokala***\n" + randomText;
	}

	private String calculateChecksum(String fileName) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		FileInputStream fis = new FileInputStream(fileName);
		byte[] byteArray = new byte[1024];
		int bytesCount;
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		fis.close();
		byte[] bytes = digest.digest();
		StringBuilder sb = new StringBuilder();
		for (byte aByte : bytes) {
			sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
