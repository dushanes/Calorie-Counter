package dushanes.caloriecounterub;

import android.os.AsyncTask;

import java.util.Calendar;

public class dateTask extends AsyncTask<Void, Void, String> {
    private Calendar date = Calendar.getInstance();
    private String todayDate;
    private String month;
    private String dayOfWeek;

    @Override
    protected String doInBackground(Void... voids) {

        switch (date.get(Calendar.MONTH)){
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "Mar";
                break;
            case 3:
                month = "Apr";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "Jun";
                break;
            case 6:
                month = "Jul";
                break;
            case 7:
                month = "Aug";
                break;
            case 8:
                month = "Sep";
                break;
            case 9:
                month = "Oct";
                break;
            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;
        }

        switch (Calendar.DAY_OF_WEEK){
            case 1:
                dayOfWeek = "Sunday";
            case 2:
                dayOfWeek = "Monday";
            case 3:
                dayOfWeek = "Tuesday";
            case 4:
                dayOfWeek = "Wednesday";
            case 5:
                dayOfWeek = "Thursday";
            case 6:
                dayOfWeek = "Friday";
            case 7:
                dayOfWeek = "Saturday";
        }

        todayDate = dayOfWeek + ", "+ month + " " + Calendar.DATE;
        return todayDate;
    }

    @Override
    protected void onPostExecute(String s) {
        MainMenu.date.setText(s);
        super.onPostExecute(s);
    }
}
