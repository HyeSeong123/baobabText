package com.baobab.example.baobabTextBoard.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.baobab.example.baobabTextBoard.dto.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public static void mkdirs(String path) {
		File dir = new File(path);

		if (dir.exists() == false) {
			dir.mkdirs();
		}
	}

	public static void writeFile(String path, String body) {
		File file = new File(path);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(body);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean rmdir(String path) {
		return rmdir(new File(path));
	}

	public static boolean rmdir(File dirToBeDeleted) {
		File[] allContents = dirToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				rmdir(file);
			}
		}
		return dirToBeDeleted.delete();
	}

	public static Map<String, Object> mapOf(Object... args) {
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("인자를 짝수개 입력해주세요.");
		}

		int size = args.length / 2;

		Map<String, Object> map = new LinkedHashMap<>();

		for (int i = 0; i < size; i++) {
			int keyIndex = i * 2;
			int valueIndex = keyIndex + 1;

			String key;
			Object value;

			try {
				key = (String) args[keyIndex];
			} catch (ClassCastException e) {
				throw new IllegalArgumentException("키는 String 타입으로 입력해야 합니다." + e.getMessage());
			}

			value = args[valueIndex];

			map.put(key, value);
		}

		return map;
	}

	public static String getFileContents(String filePath) {
		String rs = null;
		try {
			// 바이트 단위로 파일읽기
			FileInputStream fileStream = null; // 파일 스트림

			fileStream = new FileInputStream(filePath);// 파일 스트림 생성
			// 버퍼 선언
			byte[] readBuffer = new byte[fileStream.available()];
			while (fileStream.read(readBuffer) != -1) {
			}

			rs = new String(readBuffer);

			fileStream.close(); // 스트림 닫기
		} catch (Exception e) {
			e.getStackTrace();
		}

		return rs;
	}

	public static boolean copy(String sourcePath, String destPath) {
		Path source = Paths.get(sourcePath);
		Path target = Paths.get(destPath);

		if (!Files.exists(target.getParent())) {
			try {
				Files.createDirectories(target.getParent());
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return true;
		}
		return true;
	}

	public static void copyFolder(String source, String target) {
		File sourceF = new File(source);
		File targetF = new File(target);

		copyFolder(sourceF, targetF);
	}

	public static void copyFolder(File sourceF, File targetF) {
		File[] target_file = sourceF.listFiles();
		for (File file : target_file) {
			File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
			if (file.isDirectory()) {
				temp.mkdir();
				copyFolder(file, temp);
			} else {
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(temp);
					byte[] b = new byte[4096];
					int cnt = 0;
					while ((cnt = fis.read(b)) != -1) {
						fos.write(b, 0, cnt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis.close();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void deleteFolder(String path) {
		File folder = new File(path);
		try {
			if (folder.exists()) {
				File[] folder_list = folder.listFiles();

				for (int i = 0; i < folder_list.length; i++) {
					if (folder_list[i].isFile()) {
						folder_list[i].delete();
					} else {
						deleteFolder(folder_list[i].getPath());
					}
					folder_list[i].delete();
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static String getNowDateStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String callApi(String urlStr, String... args) {
		// URL 구성 시작
		StringBuilder queryString = new StringBuilder();

		for (String param : args) {
			if (queryString.length() == 0) {
				queryString.append("?");
			} else {
				queryString.append("&");
			}

			queryString.append(param);
		}

		urlStr += queryString.toString();
		// URL 구성 끝

		// 연결생성 시작
		HttpURLConnection con = null;

		try {
			URL url = new URL(urlStr);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000); // 최대통신시간 제한
			con.setReadTimeout(5000); // 최대데이터읽기시간 제한
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		// 연결생성 끝

		// 연결을 통해서 데이터 가져오기 시작
		StringBuffer content = null;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 연결을 통해서 데이터 가져오기 끝

		return content.toString();
	}

	public static Map<String, Object> callApiResponseToMap(String urlStr, String... args) {
		String jsonString = callApi(urlStr, args);

		ObjectMapper mapper = new ObjectMapper();

		try {
			return (Map<String, Object>) mapper.readValue(jsonString, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Object callApiResponseTo(Class cls, String urlStr, String... args) {
		String jsonString = callApi(urlStr, args);

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(jsonString, cls);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getJsonText(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String rs = "";
		try {
			rs = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
