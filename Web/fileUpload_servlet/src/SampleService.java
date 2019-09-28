import java.util.List;

class SampleService {
    SampleVO vo;
    SampleDAO dao;

    public SampleService(){
        vo = new SampleVO();
    }

	public void addSample(SampleVO vo) {
		dao.insertNewSample(vo);
    }

	public List<SampleVO> listSample() {
		List<SampleVO> list = dao.selectAllSamples();
		return list;
	}

	public int extraction_int(){
		int result = dao.extraction_num();
		return result;
	}

}