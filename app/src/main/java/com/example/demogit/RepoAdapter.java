package com.example.demogit;

import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Scanner;

public class RepoAdapter  extends RecyclerView.Adapter<RepoAdapter.MyviewHolder> {
    private List<Repository> repoList;
    public class MyviewHolder extends RecyclerView.ViewHolder {
        public TextView reponame,repodetails,lang,stars,fork,startoday;
        public ImageView star,forks,starToday;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            reponame = (TextView)itemView.findViewById(R.id.Repo_Name);
            repodetails = (TextView)itemView.findViewById(R.id.Repo_Details) ;
            lang= (TextView)itemView.findViewById(R.id.lang);
            stars = (TextView)itemView.findViewById(R.id.star) ;
           fork= itemView.findViewById(R.id.forks);
            startoday = (TextView)itemView.findViewById(R.id.startoday) ;
            star = itemView.findViewById(R.id.stars) ;
            forks=itemView.findViewById(R.id.fork);
            starToday = itemView.findViewById(R.id.starss) ;
        }
    }
    public RepoAdapter(List<Repository> userList) {
        this.repoList = userList;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rep_list,viewGroup,false);
        return new MyviewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {
        Repository repository = repoList.get(position);
        String text = repository.getUrl();
        myviewHolder.reponame.setClickable(true);
        myviewHolder.reponame.setMovementMethod(LinkMovementMethod.getInstance());
        myviewHolder.reponame.setText(repository.getAuthor()+"/"+repository.getName()+"\n"+ Html.fromHtml(text));

        myviewHolder.repodetails.setText(repository.getDescription()+"\n");

        if(repository.getLanguageColor() != null) {
            myviewHolder.lang.setText(repository.getLanguage() + "\t\t\t\t");
            myviewHolder.lang.setTextColor(Color.parseColor(repository.getLanguageColor()));
        }
        myviewHolder.star.setImageResource(R.drawable.star);
        myviewHolder.stars.setText(repository.getStars().toString()+"\t\t\t");
        myviewHolder.forks.setImageResource(R.drawable.fork);
        myviewHolder.fork.setText(repository.getForks().toString()+"\n");
        myviewHolder.starToday.setImageResource(R.drawable.star);
        myviewHolder.startoday.setText(repository.getCurrentPeriodStars().toString()+" "+"Stars Today");
    }
    @Override
    public int getItemCount() {
        return repoList.size();
    }
}
