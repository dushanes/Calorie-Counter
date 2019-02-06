package dushanes.caloriecounterub.Tasks;

import android.os.AsyncTask;

import dushanes.caloriecounterub.searchPage;

public class encodeTask extends AsyncTask <String, Void, String> {
    private String data = "";
    private final String apiAuth = "&app_id=d99138c6&app_key=64b5b1e02023d222cc5c606b5149c7d2";

    @Override
    protected String doInBackground(String... strings) {
        String target = "https://api.edamam.com/api/food-database/parser?ingr=";
        String temp = "";
        //String searchText = strings[0];

        //char searchParam[] = strings.toString().toCharArray();
        for (int i = 0; i < strings[0].length(); i++) {
            if (strings[0].charAt(i) == ' ') {
                temp = temp.concat("%20");
            } else {
                temp = temp.concat(String.valueOf(strings[0].charAt(i)));
            }
        }

        target = target.concat(temp);
        target = target.concat(apiAuth);
        return  target;
    }

    @Override
    protected void onPostExecute(String encodedSearch) {
        searchPage.setTarget(encodedSearch);
        super.onPostExecute(encodedSearch);
    }
}
