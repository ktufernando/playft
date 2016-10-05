package ar.com.jf.antilavado.repository.contant;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by fvaldes on 05/04/2016.
 */
public enum AlertFrequency {

    DAILY {
        @Override
        public DateTime getDateTime() {
            return DateTime.now().minusDays(1);
        }

        @Override
        public String getLabel() {
            return "Estimación Diaria";
        }

        @Override
        public String getPeriodDesc(DateTime date) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
            return dtf.print(date);
        }
    }, WEEKLY {
        @Override
        public DateTime getDateTime() {
            return DateTime.now().minusWeeks(1);
        }

        @Override
        public String getLabel() {
            return "Estimación Semanal";
        }

        @Override
        public String getPeriodDesc(DateTime date) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("'Semana %d' de MM/yyyy");
            String baseFormat = dtf.print(date); // 2014-06_%d
            int weekOfMonth = date.getDayOfMonth() % 7;
            return String.format(baseFormat, weekOfMonth);
        }
    }, MONTHLY {
        @Override
        public DateTime getDateTime() {
            return DateTime.now().minusMonths(1);
        }

        @Override
        public String getLabel() {
            return "Estimación Mensual";
        }

        @Override
        public String getPeriodDesc(DateTime date) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/yyyy");
            return dtf.print(date);
        }
    };

    public abstract DateTime getDateTime();
    public abstract String getLabel();
    public abstract String getPeriodDesc(DateTime date);
}
