package testactivity.example.com.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class newsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context,String newsTitle,String newsContent)
    {
        Intent intent = new Intent (context,newsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("news_title");//获取新传入的标题
        String newsContent = getIntent().getStringExtra("news_content");//获取新传入的内容
        NewsContentFragment newsContentFragment = (NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent); // 刷新 NewsContent 界面
    }

}
