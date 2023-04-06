// STEFANIE BAXTER 501040808
import java.util.ArrayList;

public class Season {
	
	private ArrayList<String> episodes; // strings of audiofiles 
	private ArrayList<String> episodeTitles; // strings of episode titles
	private ArrayList<Integer> episodeLengths; // ints in minutes of episode lengths
	
	public Season(ArrayList<String> episodes, ArrayList<String> episodeTitles, ArrayList<Integer> episodeLengths) {
		// constructor
		this.episodes=episodes;
		this.episodeTitles=episodeTitles;
		this.episodeLengths=episodeLengths;
	}
	
	// GETTERS & SETTERS
	public ArrayList<String> getEpisodes() {
		return this.episodes;
	}
	public void setEpisodes(ArrayList<String> episodes) {
		this.episodes=episodes;
	}
	public ArrayList<String> getEpisodeTitles() {
		return this.episodeTitles;
	}
	public void setEpisodeTitles(ArrayList<String> episodeTitles) {
		this.episodeTitles=episodeTitles;
	}
	public ArrayList<Integer> getEpisodeLengths() {
		return this.episodeLengths;
	}
	public void setEpisodeLengths(ArrayList<Integer> episodeLengths) {
		this.episodeLengths=episodeLengths;
	}
	
	// METHODS
	public String getAudioFile() { // used to create podcast objects. Combines all episodes into one file for the complete audio file
		String file="";
		for (int i=0;i<episodes.size(); i++) {
			file += episodes.get(i);
			file += "\n";
		}
		return file;
	}
	public boolean validEpisode(int i) {
		if (i>0 && i<=episodes.size()) { // checks if episode is valid
			return true;
		}
		return false;
	}
	
	public int getSeasonLength() { 
		int seasonLength=0;
		for (int i=0;i<episodeLengths.size();i++) {
			seasonLength+=episodeLengths.get(i); // adds up all episode lengths
		}
		return seasonLength; // self explanatory 
	}
	
	public String getEpisode(int i) {
		if (this.validEpisode(i)) {
		return this.episodes.get(i-1); // returns proper java indexed audio file
		}
		return "";
	}
	
	public String getEpisodeTitle(int i) {
		if (this.validEpisode(i)) {
			return this.episodeTitles.get(i-1); // returns proper java indexed episode title
		}
		return "";
	}
	
	public int getEpisodeLength(int i) {
		if (i>0 && i<=episodeLengths.size()) {
			return this.episodeLengths.get(i-1); // returns proper java indexed episode length
		}
		return 0;
	}
	
	public void printTitles() {
		for (int i=0; i<episodeTitles.size();i++) {
			int index_to_print=i+1; // creates proper index
			System.out.println("Episode " + index_to_print + ". "+ episodeTitles.get(i)); // prints each index and title
			System.out.println(); // new line
		}
	}

}
