import { TestBed } from '@angular/core/testing';
import { Article } from './article';
import { NewsService } from './news.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('NewsService', () => {
  let article = new Article();
  article = {
    articleId: "article123",
    author: "authorname",
    count:1,
    title: "aaj tak",
    url: "new url",
    urlToImage: "new url to image",
    comments: "new comments",
    content:"news content",
    description: "description of news",
    publishedAt:"published today",
    source: {
      sourceId: 123,
      name: "sourcename"     
    }
  };

  const springEndPoint = "http://localhost:8073/favouriteservice/api/v1/favouriteservice/";
  // this.springEndPoint = 'http://localhost:8073/favouriteservice/api/v1/favouriteservice/';
  // this.recommendSpringPoint = 'http://localhost:8073/articleRecommendationService/api/v1/recommendedservice/';
  const recommendSpringPoint = 'http://localhost:8073/articleRecommendationService/api/v1/recommendedservice/';
  let newsService: NewsService;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [NewsService]
    });
    newsService = TestBed.get(NewsService);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
  //  const service: NewsService = TestBed.get(NewsService);
    expect(newsService).toBeTruthy();
  });

  it('#addArticleToFavouriteList() should fetch proper response from Http call', () => {
    newsService.addArticleToFavouriteList(article).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(article);
    });
    const url = springEndPoint + "user/" + "test" + "/article";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getAllArticlesforFavouriteList() should fetch proper response from Http call', () => {
    newsService.getAllArticlesforFavouriteList().subscribe(res => {
    });
    const url = springEndPoint + "user/" + "test" + "/articles";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#deleteArticleFromFavouriteList() should fetch proper response from Http call', () => {
    newsService.deleteArticleFromFavouriteList(article).subscribe(res => {
    
    });
    const url = springEndPoint + "user/" + "test" + "/" +article.articleId;
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('DELETE');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#updateComments() should fetch proper response from Http call', () => {
    newsService.updateComments(article).subscribe(res => {
      expect(res.body).toBe(article);
    });
    const url = springEndPoint + "user/" + "test" + "/article";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('PATCH');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getAllArticlesforRecommendedList() should fetch proper response from Http call', () => {
    newsService.getAllArticlesforRecommendedList().subscribe(res => {
    });
    const url = recommendSpringPoint + "/articles";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });


});
