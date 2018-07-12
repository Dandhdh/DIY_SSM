package com.dyy.lucene;

import com.dyy.pojo.User;
import com.dyy.util.*;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * 索引类
 */
public class LuceneIndex {

	private Directory dir=null;
	/**
	 * 获取IndexWriter实例
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter()throws Exception{
		dir= FSDirectory.open(Paths.get("F://lucene"));
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer(); // 中文分词器
		IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
		IndexWriter writer=new IndexWriter(dir, iwc);
		return writer;
	}

	/**
	 * 添加索引
	 * @param user
	 */
	public void addIndex(User user)throws Exception{
		IndexWriter writer=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(user.getUserId()), Field.Store.YES));
		/**
		 * yes是会将数据存进索引，
		 * 如果查询结果中需要将记录显示出来就要存进去，
		 * 如果查询结果只是显示标题之类的就可以不用存，而且内容过长不建议存进去
		 */
		doc.add(new TextField("username", user.getUsername(), Field.Store.YES));
		doc.add(new TextField("description",user.getDescription(), Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
	}

	/**
	 * 更新博客索引
	 * @param user
	 * @throws Exception
	 */
	public void updateIndex(User user)throws Exception{
		IndexWriter writer=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(user.getUserId()), Field.Store.YES));
		doc.add(new TextField("username", user.getUsername(), Field.Store.YES));
		doc.add(new TextField("description",user.getDescription(), Field.Store.YES));
		writer.updateDocument(new Term("id", String.valueOf(user.getUserId())), doc);
		writer.close();
	}

	/**
	 * 删除指定博客的索引
	 * @param userId
	 * @throws Exception
	 */
	public void deleteIndex(String userId)throws Exception{
		IndexWriter writer=getWriter();
		writer.deleteDocuments(new Term("id", userId));
		writer.forceMergeDeletes(); // 强制删除
		writer.commit();
		writer.close();
	}

	/**
	 * 查询
	 * @param q 查询关键字
	 * @return
	 * @throws Exception
	 */
	public List<User> searchBlog(String q)throws Exception{
		dir= FSDirectory.open(Paths.get("F://lucene"));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is=new IndexSearcher(reader);
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		QueryParser parser=new QueryParser("username",analyzer);
		Query query=parser.parse(q);
		QueryParser parser2=new QueryParser("description",analyzer);
		Query query2=parser2.parse(q);
		booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
		TopDocs hits=is.search(booleanQuery.build(), 100);
		QueryScorer scorer=new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		// 标记为红色
		SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		List<User> userList=new LinkedList<User>();
		for(ScoreDoc scoreDoc:hits.scoreDocs){
			Document doc=is.doc(scoreDoc.doc);
			User user=new User();
			user.setUserId(Integer.parseInt(doc.get(("id"))));
			user.setDescription(doc.get(("description")));
			String username=doc.get("username");
			String description=doc.get("description");
			if(username!=null){
				TokenStream tokenStream = analyzer.tokenStream("username", new StringReader(username));
				String husername=highlighter.getBestFragment(tokenStream, username);
				if(StringUtil.isEmpty(husername)){
					user.setUsername(username);
				}else{
					user.setUsername(husername);
				}
			}
			if(description!=null){
				TokenStream tokenStream = analyzer.tokenStream("description", new StringReader(description));
				String hContent=highlighter.getBestFragment(tokenStream, description);
				if(StringUtil.isEmpty(hContent)){
					if(description.length()<=200){
						user.setDescription(description);
					}else{
						user.setDescription(description.substring(0, 200));
					}
				}else{
					user.setDescription(hContent);
				}
			}
			userList.add(user);
		}
		return userList;
	}
}
