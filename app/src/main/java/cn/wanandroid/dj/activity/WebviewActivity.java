package cn.wanandroid.dj.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import cn.wanandroid.dj.R;

public class WebviewActivity extends BaseActivity {
    @BindView(R.id.mLinearLayout)
    CoordinatorLayout mLinearLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitleTextView;
    private String url;
    private AgentWeb mAgentWeb;

    public static void startatcitity(Context context,String url){
        Intent intent =new Intent(context,WebviewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_webview;
    }

    @Override
    public void initLayout() {
        url=getIntent().getExtras().getString("url");
        toolbar.setTitle("");
        initToolBar(toolbar,true,"");

        mAgentWeb=AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(mLinearLayout, new CoordinatorLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
//                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
//                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public void initListener() {

    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mTitleTextView != null) {
                mTitleTextView.setText(title);
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

}
