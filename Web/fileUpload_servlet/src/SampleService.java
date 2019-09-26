import java.util.List;

class SampleService {
    SampleVO vo;
    SampleDAO dao;

    public SampleService(){
        vo = new SampleVO();
    }

	public void addSample(SampleVO vo) {
    }

	public List<SampleVO> listArticles() {
		List<SampleVO> list = dao.selectAllArticles();
		return list;
	}
	
	public void addArticle(SampleVO vo){
		dao.insertNewArticle(vo);		
	}

}