import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class writes information in the system to memory
 *
 * @author Daniel Created on 5/12/2017
 */
public class Writer {

	/**
	 * Method to write a user text file containing all of the users information
	 * 
	 * @param user
	 *            - user of the file
	 * @throws IOException
	 *             - if user cannot be saved
	 */
	public static void writeUserFile(User user) throws IOException {
		String path = "userFiles//users//" + user.getUsername() + ".txt";
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(user.getTextFileOutput());
			writer.close();
		} catch (IOException e) {
			throw new IOException("error writing to file of user " + user.getUsername());
		}

	}

	/**
	 * Method to write a custom gallery text file
	 * @param user - user of the custom gallery
	 * @param gallery - the custom gallery
	 * @throws IOException
	 */
	public static void createCustomGallery(User user, CustomGallery gallery) throws IOException {
		File file = new File("customGalleries//" + user.getUsername() + "//");
		file.mkdir();
		String path = "customGalleries//" + user.getUsername() + "//" + gallery.getName() + ".txt";
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(gallery.getTextFileOutput());
			writer.close();
			gallery.setPath(path);
		} catch (IOException e) {
			throw new IOException("error writing to file of user " + user.getUsername());
		}

	}

	/**
	 * Method to add an artwork to a custom gallery
	 * @param user - user of the custom gallery
	 * @param artwork - artwork to be added
	 * @param gallery - gallery which the artwork is being added to
	 */
	public static void addArtworkToGallery(User user, Artwork artwork, CustomGallery gallery) {
		File file = new File("customGalleries//" + user.getUsername() + "//" + gallery.getName() + ".txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(file, true));
			writer.println(artwork.getTitle());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to write a text file containing the users last log in date
 	 * @param user
	 * @throws IOException
	 */
	public static void addLogin(User user) throws IOException {
		String path = "userFiles//" + user.getUsername() + ".txt";
		try {
			PrintWriter writer = new PrintWriter(
					new FileOutputStream(new File("userFiles//logs//" + user.getUsername() + "_log" + ".txt"), true));

			Date date = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

			String d = dateFormatter.format(date);

			writer.println(d);
			writer.close();
		} catch (IOException e) {
			throw new IOException("error writing to file of user " + user.getUsername());
		}
	}

	/**
	 * Method to create and append a text file of a unique users bid history
	 * 
	 * @param bid
	 *            - the bids a user has made
	 * @throws IOException
	 *             - error if file cannot be written
	 */
	public static void writeBidFile(Bid bid) throws IOException {
		String path = "bids//" + bid.getBidder().getUsername() + ".txt";
		System.out.println(path);
		try {
			File file = new File("bids//" + bid.getBidder().getUsername() + ".txt");
			PrintWriter writer = new PrintWriter(new FileWriter(file, true));

			writer.println(bid.getTextFileOutput());
			writer.close();
		} catch (IOException e) {
			throw new IOException("Error creating bid file");
		}
	}

	/**
	 * Method to write a painting text file containing all of the paintings
	 * information
	 * 
	 * @param painting
	 *            - painting of the file
	 * @throws IOException
	 *             - catch error if file cannot be found
	 */
	public static void writePaintingFile(Painting painting) throws IOException {
		String path = "artworkFiles//paintings//" + painting.getTitle() + ".txt";
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(painting.getTextFileOutput());
			writer.close();
		} catch (IOException e) {
			throw new IOException("Error creating painting file for " + painting.getTitle());
		}
	}

	/**
	 * Method to write a sculpture text file containing all of the sculptures
	 * information
	 * 
	 * @param sculpture
	 *            - sculpture of the file
	 * @throws IOException
	 *             if sculpture cannot be saved
	 */
	public static void writeSculptureFile(Sculpture sculpture) throws IOException {
		String path = "artworkFiles//sculptures//" + sculpture.getTitle() + ".txt";
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(sculpture.getTextFileOutput());
			writer.close();
		} catch (IOException e) {
			throw new IOException("Error creating sculpture file for " + sculpture.getTitle());
		}
	}

	/**
	 * Method to add 2 users into a favourites text file
	 * 
	 * @param user1
	 *            - the main user
	 * @param user2
	 *            - the user that the main user wants to favourite
	 */
	public static void addToFavourites(User user1, User user2) {

		File file = new File("favourites.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(file, true));

			writer.println(user1.getUsername() + "," + user2.getUsername());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to remove users from the favourite text file
	 * 
	 * @param user1
	 *            - one of the user that needs to be removed
	 * @param user2
	 *            - the other user that needs to be removed
	 * @return boolean - true if users were successfully removed, false otherwise
	 */
	public static boolean removeFromFavourites(User user1, User user2) {

		final File FAVOURITES = new File("favourites.txt");
		final File TEMP_FILE = new File("temp-favourites.txt");
		String toRemove = user1.getUsername() + "," + user2.getUsername();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(FAVOURITES));
			BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE));
			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				if (null != currentLine && !currentLine.equalsIgnoreCase(toRemove)) {
					writer.write(currentLine + System.getProperty("line.separator"));
				}
			}
			writer.close();
			reader.close();
			FAVOURITES.delete();
			boolean successful = TEMP_FILE.renameTo(FAVOURITES);
			System.out.println(successful);
			return successful;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean removeFromGallery(CustomGallery customGallery, Artwork removed) throws FileNotFoundException, UnsupportedEncodingException {
		final File ORIGINAL = new File(customGallery.getPath());
		final File TEMP_FILE = new File(customGallery.getPath()+"1.txt");
		String toRemove = removed.getTitle();
		String line = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(ORIGINAL));
			BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE));
			while ((line = reader.readLine()) != null) {
				if (null != line && !line.equalsIgnoreCase(toRemove)) {
					writer.write(line + System.getProperty("line.separator"));
				}
			}
			writer.close();
			reader.close();
			ORIGINAL.delete();
			boolean successful = TEMP_FILE.renameTo(ORIGINAL);
			return successful;
		}
		catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}