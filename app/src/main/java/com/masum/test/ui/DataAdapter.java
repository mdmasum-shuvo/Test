package com.masum.test.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.masum.Listener.ItemClickListener;
import com.masum.datamodel.Resource;
import com.masum.test.R;
import com.masum.test.databinding.ItemViewBinding;

import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewFilesHolder> {
    private static final String TAG = "MyProspectAdapter";
    private List<Resource> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private ExoPlayer exoPlayer;
    private static ItemClickListener mListener;

    public DataAdapter(Context context, List<Resource> list) {

        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewFilesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemViewBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_view, parent, false);
        return new ViewFilesHolder(binding);


    }

    @Override
    public void onBindViewHolder(final ViewFilesHolder holder, int position) {

        holder.binding.setData(list.get(position));
        Glide.with(context)
                .load(list.get(position).getOwner().getAvatar())
                .into(holder.binding.avater);

        if (list.get(position).getPhoto().equals("")) {

            holder.binding.photo.setVisibility(View.GONE);
            holder.binding.videoView.setVisibility(View.VISIBLE);
            playVideo(holder, position);
        } else {


            holder.binding.photo.setVisibility(View.VISIBLE);
            holder.binding.videoView.setVisibility(View.GONE);

            Glide.with(context)
                    .load(list.get(position).getPhoto())
                    .into(holder.binding.photo);
        }

    }


    private void playVideo(final ViewFilesHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getVideo());
        holder.binding.videoView.setVideoURI(uri);
        holder.binding.videoView.start();

        holder.binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //close the progress dialog when buffering is done
                // pd.dismiss();
            }
        });
    }

 /*   private void initExoPlayerLive(final ViewFilesHolder holder, int position) {

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoFactory);

        // Create Player
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);

        Handler mHandler = new Handler();
        String userAgent = Util.getUserAgent(context, "");
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory(userAgent, null, DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS, 1800000, true);
        HlsMediaSource mediaSource = new HlsMediaSource(Uri.parse(list.get(position).getVideo()), dataSourceFactory, 1800000, mHandler, null);
        holder.binding.exoPlayerView.setPlayer((SimpleExoPlayer) exoPlayer);
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.prepare(mediaSource);

    }*/


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewFilesHolder extends RecyclerView.ViewHolder {

        private final ItemViewBinding binding;

        public ViewFilesHolder(final ItemViewBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mListener != null) {
                        mListener.onitemClick(getLayoutPosition());
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mListener != null) {
                        mListener.onitemLongClick(getLayoutPosition());

                    }
                    return true;
                }
            });
        }
    }


    public void setItemClickListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }


}