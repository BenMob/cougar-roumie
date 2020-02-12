package com.SE370WebApp.cougarroomie;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class CougarRoomieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CougarRoomieApplication.class, args);

		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("src/main/java/com/SE370WebApp/cougarroomie/cougar-roomie-f13a3-firebase-adminsdk-fo26g-83a25dd03d.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		FirebaseOptions options = null;
		try {
			options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://cougar-roomie-f13a3.firebaseio.com")
					.build();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FirebaseApp.initializeApp(options);
	}


}
