// STEFANIE BAXTER 501040808
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int index = 0;
				
				System.out.print("Store Content #: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				AudioContent content = store.getContent(index);
				if (content == null)
					System.out.println("Content Not Found in Store");
				else if (!mylibrary.download(content))
						System.out.println(mylibrary.getErrorMessage());
									
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index=0;
				System.out.print("Song Number: "); // get the index
				if (scanner.hasNextInt()) {
					index=scanner.nextInt();
					scanner.nextLine();
				}
				
				boolean playable = mylibrary.playSong(index); // check if it can be played
				if (!playable) { // if not get error msg
					System.out.println(mylibrary.getErrorMessage());
				}
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index=0;
				System.out.print("Audio Book Number: "); // get index
				if (scanner.hasNextInt()) {
					index=scanner.nextInt();
					scanner.nextLine();
				}
				boolean printable = mylibrary.printAudioBookTOC(index); // check if valid entry
				if (!printable) {
					System.out.println(mylibrary.getErrorMessage()); // if not get error msg
				}
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int book_index=0;
				System.out.print("Audio Book Number: "); // get index
				if (scanner.hasNextInt()) {
					book_index=scanner.nextInt();
					scanner.nextLine();
				}
				int ch_index=0;
				System.out.print("Chapter: "); // get chapter
				if (scanner.hasNextInt()) {
					ch_index=scanner.nextInt();
					scanner.nextLine();
				}
				boolean playable=mylibrary.playAudioBook(book_index, ch_index); // check if playable
				if (!playable) {
					System.out.println(mylibrary.getErrorMessage()); // if not get error msg
				}
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				int pod_index=0;
				System.out.print("Podcast Number: "); // get the index
				if (scanner.hasNextInt()) {
					pod_index=scanner.nextInt();
					scanner.nextLine();
				}
				int season_index=0;
				System.out.print("Season: "); // get the season
				if (scanner.hasNextInt()) {
					season_index=scanner.nextInt();
					scanner.nextLine();
				}
				boolean printable = mylibrary.printPodcastEpisodes(pod_index, season_index); // check if valid
				if (!printable) {
					System.out.println(mylibrary.getErrorMessage()); // if not print error msg
				}
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) // same process as above methods
			{
				int pod_index=0;
				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt()) {
					pod_index=scanner.nextInt();
					scanner.nextLine();
				}
				int season_index=0;
				System.out.print("Season: ");
				if (scanner.hasNextInt()) {
					season_index=scanner.nextInt();
					scanner.nextLine();
				}
				int episode_index=0;
				System.out.print("Episode: ");
				if (scanner.hasNextInt()) {
					episode_index=scanner.nextInt();
					scanner.nextLine();
				}
				boolean printable = mylibrary.playPodcast(pod_index, season_index, episode_index);
				if (!printable) {
					System.out.println(mylibrary.getErrorMessage());
				}
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title="";
				System.out.print("Playlist Title: "); // get playlist
				if (scanner.hasNext()) {
					title=scanner.next();
					scanner.nextLine();
				}
				mylibrary.playPlaylist(title); // print it
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String playlistTitle="";
				System.out.print("Playlist Title: "); // get playlist
				if (scanner.hasNext()) {
					playlistTitle=scanner.next();
					scanner.nextLine();
				}
				int content=0;
				System.out.print("Content Number: ");
				if (scanner.hasNextInt()) {
					content=scanner.nextInt();
					scanner.nextLine();
				}
				mylibrary.playPlaylist(playlistTitle, content); // call method from Playlist to play 
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int song_index=0;
				System.out.print("Library Song #: "); // get song to delete
				if (scanner.hasNextInt()) {
					song_index=scanner.nextInt();
					scanner.nextLine();
				}
				mylibrary.deleteSong(song_index); // delete it 
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title="";
				System.out.print("Playlist Title: "); // get new title
				if (scanner.hasNext()) {
					title=scanner.next();
					scanner.nextLine();
				}
				boolean makeable=mylibrary.makePlaylist(title); // check if it already exists
				if (!makeable) {
					System.out.println(mylibrary.getErrorMessage()); // get error msg if needed
				}
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{ 
				String playlistTitle="";
				System.out.print("Playlist Title: "); // same process as above
				if (scanner.hasNext()) {
					playlistTitle=scanner.next();
					scanner.nextLine();
				}
				mylibrary.printPlaylist(playlistTitle);
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				String playlistTitle="";
				System.out.print("Playlist Title: "); // get title
				if (scanner.hasNext()) {
					playlistTitle=scanner.next();
					scanner.nextLine();
				}
				String type="";
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: "); // get type
				if (scanner.hasNext()) {
					type=scanner.next();
					scanner.nextLine();
				}
				int content=0;
				System.out.print("Library Content #: "); // get content index
				if (scanner.hasNextInt()) {
					content=scanner.nextInt();
					scanner.nextLine();
				}
				mylibrary.addContentToPlaylist(type, content, playlistTitle); // add it
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				String title="";
				System.out.print("Playlist Title: "); // get playlist
				if (scanner.hasNext()) {
					title=scanner.next();
					scanner.nextLine();
				}
				int content=0;
				System.out.print("Playlist Content #: "); // get index
				if (scanner.hasNextInt()) {
					content=scanner.nextInt();
					scanner.nextLine();
				}
				mylibrary.delContentFromPlaylist(content, title); // delete it
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			System.out.print("\n>");
		}
	}
}

