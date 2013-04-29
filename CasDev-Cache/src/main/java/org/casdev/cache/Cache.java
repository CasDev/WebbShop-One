package org.casdev.cache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Cache {

	private File f;

	public Cache(String directory, String filename, String filetype) {
		this.f = new File("src/main/resources/" + directory + "/" + filename
				+ "." + filetype.toLowerCase());

		File d = new File("src/main/resources/" + directory);
		d.mkdirs();

	}

	public Cache(String directory, String filename) {
		this(directory, filename, "JSON");
	}

	public Cache() {
		this("files", "file", "JSON");
	}

	public void save(Object obj, int seconds) {

		try {

			Gson gson = new Gson();
			String json = gson.toJson(obj);

			Calendar c = Calendar.getInstance();
			c.add(Calendar.SECOND, seconds);
			JsonParser parser = new JsonParser();
			JsonObject o = (JsonObject) parser.parse(json);
			o.addProperty("year", c.get(Calendar.YEAR));
			o.addProperty("month", c.get(Calendar.MONTH));
			o.addProperty("day", c.get(Calendar.DATE));
			o.addProperty("hour", c.get(Calendar.HOUR));
			o.addProperty("minute", c.get(Calendar.MINUTE));
			o.addProperty("second", c.get(Calendar.SECOND));

			if (!f.exists()) {
				f.createNewFile();
				FileWriter fs = new FileWriter(f);
				BufferedWriter out = new BufferedWriter(fs);
				out.write(o.toString());
				out.close();
			}

		} catch (IOException e) {
			// TODO: what?
		}

	}

	private File[] getAllFiles() {

		File d = f.getParentFile();
		return d.listFiles();

	}

	public <T> T get(Class<T> c, Getter<T> get) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(this.f));

			String json = br.readLine();
			br.close();

			JsonParser parser = new JsonParser();
			JsonObject o = (JsonObject) parser.parse(json);

			Calendar c1 = Cache.getDate(o);
			Calendar c2 = Calendar.getInstance();

			if (c2.after(c1)) {
				f.delete();

				return null;
			} else {

				T object = get.get(c, o);

				return object;

			}

		} catch (IOException e) {
			// TODO: what?
		}
		return null;

	}

	public static Calendar getDate(JsonObject o) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(o.get("year").toString()));
		c.set(Calendar.MONTH, Integer.parseInt(o.get("month").toString()));
		c.set(Calendar.SECOND, Integer.parseInt(o.get("second").toString()));
		c.set(Calendar.DATE, Integer.parseInt(o.get("day").toString()));
		c.set(Calendar.MINUTE, Integer.parseInt(o.get("minute").toString()));
		c.set(Calendar.HOUR, Integer.parseInt(o.get("hour").toString()));
		return c;
	}

}
