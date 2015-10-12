package friendless.statisticallanguagemodelling;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by john on 25/08/15.
 */
public class Database {
    private static final int MIN_ID = 1000;
    private static final int MAX_ID = 999999;
    private static final String GET_CORPUS_IDS = "select id from corpus";
    private static final String INSERT_CORPUS = "insert into corpus (id, passphrase, creationDate) values (?, ?, ?)";
    private static final String INSERT_STORY = "insert into story (corpus{0}) values (?{1})";
    private static final String GET_CORPUS = "select id, passphrase, creationDate from corpus where id = ?";
    private static final String DELETE_CORPUS = "delete from corpus where id = ?";
    private static final String GET_STORIES = "select element1, element2, element3, element4, element5, element6, element7, element8, element9, element10, element11, element12, element13, element14, element15, element16 from story where corpus = ?";

    private DataSource dataSource;
    private Random random = new Random();

    public Database() throws IOException {
        Properties properties = new Properties();
        properties.load(Database.class.getClassLoader().getResourceAsStream("database.properties"));
        try {
            Driver driver = (Driver) Class.forName(properties.getProperty("driver")).newInstance();
            String url = properties.getProperty("url");
            dataSource = new SimpleDriverDataSource(driver, url, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int createNewCorpus(String passphrase) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        while (true) {
            List<Integer> ids = jdbcTemplate.query(GET_CORPUS_IDS, new SingleColumnRowMapper(Integer.class));
            for (int i = 0; i < 100; i++) {
                int n = MIN_ID + random.nextInt(MAX_ID+1-MIN_ID);
                if (!ids.contains(n)) {
                    jdbcTemplate.update(INSERT_CORPUS, new Object[] { n, passphrase, new Date() });
                    return n;
                }
            }
        }
    }

    Corpus retrieveCorpus(int corpusIndex) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows = (List) jdbcTemplate.query(GET_CORPUS, new Object[] { corpusIndex }, new ColumnMapRowMapper());
        if (rows.size() == 0) throw new NotFoundException();
        Map<String, Object> corpusRow = rows.get(0);
        Corpus c = new Corpus(corpusIndex, (String) corpusRow.get("passphrase"), (Date) corpusRow.get("creationDate"));
        rows = (List) jdbcTemplate.query(GET_STORIES, new Object[] { corpusIndex }, new ColumnMapRowMapper());
        for (Map<String, Object> row : rows) {
            List<Integer> story = new ArrayList<>();
            int n = 1;
            while (n <= Parameters.LONGEST_STORY) {
                Integer s = (Integer) row.get("element" + n);
                if (s == null) break;
                story.add(s);
                n++;
            }
            if (story.size() > 0) {
                c.addStory(story);
            }
        }
        return c;
    }

    public void deleteCorpus(int corpusIndex) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(DELETE_CORPUS, new Object[] { corpusIndex });
    }

    public void addStory(int id, List<Integer> intStory) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        List<Object> args = new ArrayList<>();
        args.add(id);
        int index = 0;
        StringBuilder colNames = new StringBuilder();
        StringBuilder qMarks = new StringBuilder();
        for (Integer word : intStory) {
            index++;
            if (index >= Parameters.LONGEST_STORY) break;
            colNames.append(", element").append(index);
            qMarks.append(", ?");
            args.add(word);
        }
        String insertStatement = MessageFormat.format(INSERT_STORY, colNames, qMarks);
        Object[] argArray = args.toArray(new Object[args.size()]);
        template.update(insertStatement, argArray);
    }
}
