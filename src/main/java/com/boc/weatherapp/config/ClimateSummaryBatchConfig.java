package com.boc.weatherapp.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.boc.weatherapp.model.ClimateSummary;

/*
 * ClimateSummaryBatchConfig file takes care of reading and writing csv data into embedded H2 database.
 * As soon as user hits localhost:8080. csv data are read and written into database with necessary table generation.
 */
@Configuration
@EnableBatchProcessing
public class ClimateSummaryBatchConfig {

	/*
	 * Purpose: This bean does the job of reading csv file processes each row and writes into database in one go. 
	 * Input: Job instantiated as jobBuilderFactory, iteamReader to read csv file, itemProcessor to process each row,
	 * 			itemWriter to write into embedded database
	 * Output: returns the job builder object
	 * */
	@Bean
	public Job job(JobBuilderFactory jBuilderFactory,
			StepBuilderFactory sBuilderFactory,
			ItemReader<ClimateSummary> itemReader,
			ItemProcessor<ClimateSummary, ClimateSummary> itemProcessor,
			ItemWriter<ClimateSummary> itemWriter) 
	{
		
		Step step = sBuilderFactory.get("ClimateSummary-Data-Load")
				.<ClimateSummary, ClimateSummary>chunk(10000)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
				
		return jBuilderFactory.get("ClimateData-Load")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();	
	}
	
	/*
	 * Purpose: FlatFileItemReader reads the supplied csv file.  
	 * Input: NONE
	 * Output: returns reader object to job builder factory
	 * */
	@Bean
    public FlatFileItemReader<ClimateSummary> itemReader() {
        FlatFileItemReader<ClimateSummary> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("eng-climate-summary.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(climateSummaryLineMapper());
        return flatFileItemReader;
    }

	
	/*
	 * Purpose: this bean processes each row and maps Model objects to based on the lineTokenizer specified below. 
	 * Input: None
	 * Output: returns the linemapper which maps model with linemapper object based on the linetokenizer  
	 * */
	
	@Bean
	public LineMapper<ClimateSummary> climateSummaryLineMapper() {
		// TODO Auto-generated method stub
		DefaultLineMapper<ClimateSummary> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] {"Station_Name", "Province", "Date", "Mean_Temp", "Highest_Monthly_Maxi_Temp", "Lowest_Monthly_Min_Temp"});
		
		BeanWrapperFieldSetMapper<ClimateSummary> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(ClimateSummary.class);
		
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}
}
