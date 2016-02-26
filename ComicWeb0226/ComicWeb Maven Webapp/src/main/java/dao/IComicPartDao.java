package dao;

import model.ComicPart;
import dao.common.IOperation;

public interface IComicPartDao extends IOperation<ComicPart> {
	public ComicPart FindTopOne(String comicId);

}
