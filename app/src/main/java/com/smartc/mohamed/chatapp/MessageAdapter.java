package com.smartc.mohamed.chatapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMessages;

    public MessageAdapter(List<Message> messages) {
        mMessages = messages;
      //  mUsernameColors = context.getResources().getIntArray(R.array.username_colors);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = mMessages.get(position);
        viewHolder.setMessage(message.getMessage());
        viewHolder.setImage(message.getImage(),'S');
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mMessageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mMessageView = (TextView) itemView.findViewById(R.id.message);
        }

        public void setMessage(String message) {
            if (null == mMessageView) {
                mMessageView.setVisibility(View.GONE);
                return;
            }
            if(null == message) {
                mMessageView.setVisibility(View.GONE);
                return;
            }
            mMessageView.setVisibility(View.VISIBLE);
            char type = message.charAt(0);
            if(type=='S'){
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mMessageView.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mMessageView.setLayoutParams(params);
                message = message.substring(1, message.length());
            }
            mMessageView.setText(message);
        }

        public void setImage(Bitmap bmp,char f){
            if(null == mImageView)  {
                mImageView.setVisibility(View.GONE);
                return;
            }
            if(null == bmp) {
                mImageView.setVisibility(View.GONE);
                return;
            }
            mImageView.setVisibility(View.VISIBLE);
            if(f=='s'){
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mImageView.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mImageView.setLayoutParams(params);
            }
            mImageView.setImageBitmap(bmp);
        }

    }
}
