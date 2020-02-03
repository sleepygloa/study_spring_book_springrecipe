package recipe5_2_8.com.pojo.sequence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@DatePrefixAnnotation
public class DatePrefixGenerator implements PrefixGenerator{
	
	public String pattern;
	
	@Override
	public String getPrefix() {
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}

	@Override
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	
	
}
