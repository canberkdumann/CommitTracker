package com.example.commitTracker.Services;

import com.example.commitTracker.Model.Commit;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GitHubService {
    private static final String GITHUB_API_URL = "https://api.github.com/";
    private static final String GITHUB_API_TOKEN = System.getenv("GITHUB_API_TOKEN");

    public List<Commit> fetchGithubCommits(String owner, String repo, String since) {
        List<Commit> commits = new ArrayList<>();
        try {
            String url = GITHUB_API_URL + "repos/" + owner + "/" + repo + "/commits?since=" + since;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "token " + GITHUB_API_TOKEN);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
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

