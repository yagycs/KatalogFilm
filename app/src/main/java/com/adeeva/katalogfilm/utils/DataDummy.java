package com.adeeva.katalogfilm.utils;

import com.adeeva.katalogfilm.data.FilmEntity;

import java.util.ArrayList;

public class DataDummy {

    public static ArrayList<FilmEntity> generateDummyMovie() {

        ArrayList<FilmEntity> films = new ArrayList<>();

        films.add(new FilmEntity(
                "530915",
                "1917",
                "At the height of the First World War, two young British soldiers, Schofield and Blake are given a seemingly impossible mission. In a race against time, they must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers—Blake's own brother among them.",
                "December 10, 2019",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/AuGiPiGMYMkSosOJ3BQjDEAiwtO.jpg"
        ));

        films.add(new FilmEntity(
                "38700",
                "Bad Boys for Life",
                "Marcus and Mike are forced to confront new threats, career changes, and midlife crises as they join the newly created elite team AMMO of the Miami police department to take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel.",
                "January 17, 2020",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg"
        ));

        films.add(new FilmEntity(
                "449924",
                "Ip Man 4: The Finale",
                "Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son.",
                "December 25, 2019",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/yJdeWaVXa2se9agI6B4mQunVYkB.jpg"
        ));

        films.add(new FilmEntity(
                "181812",
                "Star Wars: The Rise of Skywalker",
                "The surviving Resistance faces the First Order once again as the journey of Rey, Finn and Poe Dameron continues. With the power and knowledge of generations behind them, the final battle begins.",
                "December 20, 2019",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/db32LaOibwEliAmSL2jjDF6oDdj.jpg"
        ));

        films.add(new FilmEntity(
                "331482",
                "Little Women",
                "Four sisters come of age in America in the aftermath of the Civil War.",
                "December 25, 2019",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/mSmiB8XjUnR1GSIljuCPGsk0cwX.jpg"
        ));

        return films;
    }

    public static ArrayList<FilmEntity> generateDummyTv(){

        ArrayList<FilmEntity> tvs = new ArrayList<>();

        tvs.add(new FilmEntity(
                "1412",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "October 10, 2012",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
        ));

        tvs.add(new FilmEntity(
                "44217",
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "March 3, 2013",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/ff1zhqvwfS5HvRNcA5UFrH0PA2q.jpg"
        ));

        tvs.add(new FilmEntity(
                "4057",
                "Criminal Minds",
                "An elite team of FBI profilers analyze the country's most twisted criminal minds, anticipating their next moves before they strike again. The Behavioral Analysis Unit's most experienced agent is David Rossi, a founding member of the BAU who returns to help the team solve new cases.",
                "September 22, 2005",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/7TCwgX7oQKxcWYEhSPRmaHe6ULN.jpg"
        ));

        tvs.add(new FilmEntity(
                "69050",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "January 26, 2017",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg"
        ));

        tvs.add(new FilmEntity(
                "4614",
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "September 23, 2003",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg"
        ));

        return tvs;
    }
}
