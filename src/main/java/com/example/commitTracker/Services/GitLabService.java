package com.example.commitTracker.Services;

import com.example.commitTracker.Model.Commit;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GitLabService {
    private static final String GITLAB_API_URL = "https://gitlab.com/api/v4";
    private static final String GITLAB_API_TOKEN = System.getenv("GITLAB_API_TOKEN");

    public List<Commit> fetchGitLabCommits(String username, String repo) {
        List<Commit> commits = new ArrayList<>();
        try {
            String url = GITLAB_API_URL + "/projects/" + URLEncoder.encode(username + "/" + repo, "UTF-8") + "/repository/commits";
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + GITLAB_API_TOKEN);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            Gson gson = new Gson();
            Commit[] commitArray = gson.fromJson(content.toString(), Commit[].class);
            commits = Arrays.asList(commitArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commits;
    }
}

