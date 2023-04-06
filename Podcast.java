// STEFANIE BAXTER 501040808
import java.util.ArrayList;
public class Podcast extends AudioContent {
	
	public static final String TYPENAME =	"PODCAST";
	
	private String host;
	private ArrayList<Season> seasons;
	
	public Podcast (String title, String id, int year, String type, String audioFile, int length, String host, ArrayList<Season> seasons) {
		super(title, year, id, type, audioFile, length);
		this.host=host;
		this.seasons=seasons;
	}
	
	// GETTERS & SETTERS
	public String getHost() {
		return this.host;
	}
	public void setHost(String host) {
		this.host=host;
	}
	public ArrayList<Season> getSeasons(){
		return this.seasons;
	}
	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons=seasons;
	}
	public String getType() {
		return TYPENAME;
	}
	// for other attributes call super.getWhatever
	
	// METHODS
	public boolean validSeason(int i) {
		if (i>0 && i<=seasons.size()) { // check if season is valid. useful in other methods
			return true;
		}
		return false;
	}
	public int getNumberOfSeasons() { // used in other method
		return this.seasons.size();
	}
	
	public void printInfo() {
		super.printInfo(); // call super class print info
		System.out.println("Host: "+this.host); // and add podcast info
		System.out.println("Seasons: " + this.getNumberOfSeasons());
	}
	
	public boolean podcastSeasonTOC(int szn) {
		if (this.validSeason(szn)) { // check if season is valid
			Season myszn=seasons.get(szn-1); // get the correct index
			myszn.printTitles(); // call printTitles from Season class
			return true;
		}
		return false;
	}
	
	public boolean play(int season_index, int episode_index) {
		if (this.validSeason(season_index)) { // validate season
			Season season_to_get=seasons.get(season_index-1); // get correct index
			if (season_to_get.validEpisode(episode_index)) {
				this.printInfo(); // call print info
				System.out.println();
				System.out.println("" + season_to_get.getEpisodeTitle(episode_index) + "."); // print the episode title
				System.out.println(season_to_get.getEpisode(episode_index)); // print the episode "audio" file
				return true;
			}
			
		}
		return false;
	}
	
	public boolean equals(Podcast other_pod) {
		// 2 podcasts are equal if they have the same AudioContent info and host (what I decided on)
		Podcast newpod=(Podcast) other_pod;
		return (super.equals(newpod) && this.host.equals(newpod.getHost()));	// check conditions
	}
}
