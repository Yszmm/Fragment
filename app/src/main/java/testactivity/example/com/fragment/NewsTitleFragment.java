package testactivity.example.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.id.list;

/**
 * Created by Yszm on 2018/10/22.
 */

public class NewsTitleFragment extends Fragment {
    private boolean isTwopane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_frag,container,false);
         //recyclerview设置layoutmanager和adapter
         RecyclerView newsTitleRecyclerView= (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
       newsTitleRecyclerView.setLayoutManager(layoutManager);
         //参数是News类的集合，List<News>
         NewsAdapter adapter=new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;

    }
    private List<News> getNews () {
        List<News> newsList = new ArrayList<>();
        for (int i=1; i<=50;i++)
        {
            News news = new News();
            news.setTitle("This is news title" + i );
           news.setContent(getRandomLengthContent("This is news content"+i));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i<length;i++){
            builder.append(content);
        }
        return builder.toString();
    }
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewslist;

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View view)
            {
                super(view);
                newsTitleText = (TextView)   view.findViewById(R.id.news_title);
            }
        }
        public  NewsAdapter(List<News> newsList)
        {
            mNewslist = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(    new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    News news = mNewslist.get(holder.getAdapterPosition());
                    //如果是双页刷新news content activity 中的内容
                    if(isTwopane){
                        NewsContentFragment newsContentFragment =
                                (NewsContentFragment)  getFragmentManager()
                                        .findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }
                    else //如果是单页则直接启动 news content activity
                    {
                        newsContentActivity.actionStart(getActivity(),
                                news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewslist.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewslist.size();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null)
        {
            isTwopane = true;
        }else
        {
            isTwopane=  false;
        }
    }
}
