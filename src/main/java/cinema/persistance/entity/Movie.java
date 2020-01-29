package cinema.persistance.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "movies")
public class Movie {
	
	@Column
	private Integer idMovie;
	private String title;
	private String originalTitle;
	private Integer year;
	private Integer duration;
	private List<String> genres = new ArrayList<String>();
	private Float rating;
	private Classification clasification;
	private String synopsis;
	private Person director;
	private String format;
	
	private List<Actor> actors = new ArrayList<>();

	public Movie() {
        super();
    }
    
    public Movie(String title, int year) {
        this(null, title, null,year, null, null, null, null, null, null );
    }
    
    public Movie(String title, Integer year, Integer duration) {
        this(null, title, null, year , duration, null, null, null, null, null );
    }
    
    public Movie(String title, int year, int duration, Person director) {
        this(null, title, null, year, duration, null, null, null, null, director );
    }
    
    public Movie(String title, int year, int duration, List<String> genres) {
        this(null, title, null, year, duration, genres, null, null, null, null );
    }
    
    public Movie(String title, int year, int duration, List<String> genres, Person director) {
        this(null, title, null, year, duration, genres, null, null, null, director );
    }
    
    public Movie(String title, String originalTitle, int year, int duration) {
        this(null, title, originalTitle, year, duration, null, null, null, null, null );
    }
    
    public Movie(String title, String originalTitle, Integer year, String synopsis, String format, Person director) {
        this(null, title, originalTitle, year, null, null, null, synopsis, format, director );
    }

    public Movie(String title, String originalTitle, Integer year, Integer duration, String synopsis, String format, Person director) {
        this(null, title, originalTitle, year, duration, null, null, synopsis, format, director );
    }

	public Movie(Integer idMovie, String title, String originalTitle, Integer year, Integer duration, List<String> genres, Float rating, String synopsis, String format, Person director) {
		super();
		this.idMovie = idMovie;
		this.title = title;
		this.originalTitle = originalTitle;
		this.year = year;
		this.duration = duration;
		this.genres = genres;
		this.rating = rating;
		this.synopsis = synopsis;
		this.director = director;
		this.format = format;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movie")
	public Integer getId_movie() {
		return idMovie;
	}

	public void setId_movie(Integer idMovie) {
		this.idMovie = idMovie;
	}

	@Column(nullable = false, length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "original_title", nullable = true, length = 255)
	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	@Column(nullable = false)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	@ElementCollection
	@CollectionTable(name = "genres",
		joinColumns=
	        @JoinColumn(name="id_movie")
	)
	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	@ManyToOne
	@JoinColumn(name="id_director", nullable=true)
	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}
	
	@Column(name = "rating", nullable = true)
	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	@Column(name = "clasification", nullable = true)
	public Classification getClasification() {
		return clasification;
	}

	public void setClasification(Classification clasification) {
		this.clasification = clasification;
	}

	@Column(name = "resume", nullable = true, length = 65535)
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getFormat() {
		return format;
	}

	
	public void setFormat(String format) {
		this.format = format;
	}
	
	@Transient
	@OneToMany(targetEntity = Actor.class, mappedBy = "person")
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(title); //pour eviter de faire de + "" + ""+..etc
		return builder.append(" (")
				.append(year)
				.append(')')
				.append('#')
				.append(idMovie)
				.toString(); 
	}

}
