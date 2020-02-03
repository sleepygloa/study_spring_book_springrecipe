package recipe5_2_3.com.pojo.sequence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
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
