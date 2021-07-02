package services;

import beans.GradeFormat;
import repos.GradeFormatRepo;

public class GradeFormatServiceImpl implements GradeFormatService {

	GradeFormatRepo gf = new GradeFormatRepo();
	@Override
	public GradeFormat getById(int id) {
		return gf.getById(id);
	}

}
