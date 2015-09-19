package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
*
* Filterクラス
* Servlet,JSPの前後に位置し、実行前後に、リクエスト、レスポンスに対して
* 任意の処理を行う。
* 複数のクラスに対して、共通の処理を記述できる
*
* @author yoshitomi
*
*/
public class EncodingFilter implements Filter {


	public void doFilter(ServletRequest paramServletRequest,
							ServletResponse paramServletResponse,
							FilterChain paramFilterChain) throws IOException, ServletException {



		//文字コードセット(入力パラメータ用)
		paramServletRequest.setCharacterEncoding("UTF-8");
		paramServletResponse.setContentType("text/html;charset=UTF-8");
		//実行
		paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
