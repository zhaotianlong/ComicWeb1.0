package service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import model.Author;
import model.Comic;
import model.ComicPage;
import model.ComicPart;
import model.ComicTab;
import model.ComicTrend;
import model.Tab;
import model.vo.AuthorComicModel;
import model.vo.ComicTrendModel;

public interface IAuthorService {
	public void SaveAuthorInfo(Author a);
	public String CutoutIcon(MultipartFile file,int x, int y, int w, int h,int selectW,int selectH,String iconName) throws IOException;
	public List<AuthorComicModel> GetAuthorComic(String authorId,String orderby,int pre,int max);
	public int GetSumAC(String authorId);
	public Comic GetComic(String comicId);
	public void InsertComicTab(ComicTab ct);
	public void DeleteComicTab(Comic c,String tabid);
	public Tab GetTab(String tabdid);
	public void SaveComicInfo(Comic c);
	public void CreateComicPage(ComicPage cp);
	public void CreateComicPart(ComicPart cp);
	public ComicPart GetComicPart(String partId);
	public ComicPage GetComicPage(String pageId);
	public void SavePage(ComicPage cp);
	public Author GetAuthor(String authorId);
	public void CreateComic(Comic c);
	public void CreateComicTab(ComicTab ct);
	public void SaveComicPart(ComicPart cp);
	public int GetSumAU(String authorId);
	/***
	 * 删除漫画
	 * @param comicId string类型 漫画id
	 */
	public void DeleteComic(String comicId);
	/***
	 * 删除漫画单个页
	 * @param pageId string类型 页id
	 */
	public void DeleteComicPage(String pageId);
	/***
	 * 获取章节中最大的id
	 * @param partId
	 * @return
	 */
	public int GetMaxIdPage(String partId);
	/***
	 * 获取漫画动态
	 * @param authorId 作者id
	 * @param pre 起始条数
	 * @param max 最大记录
	 * @return
	 */
	public List<ComicTrendModel> GetComicTrend(String authorId,int pre,int max);
	/***
	 * 获取漫画动态记录总数
	 * @param authorId
	 * @return
	 */
	public int GetComicTrendSum(String authorId);
	/***
	 * 创建漫画动态
	 * @param ct 动态对象
	 */
	public void CreateComicTrend(ComicTrend ct);
	/***
	 * 获取漫画最新章节序列数
	 * @param comicId 漫画id
	 * @return
	 */
	public int GetPartMaxId(String comicId);
}
