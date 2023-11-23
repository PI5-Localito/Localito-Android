package mx.pi5.localito.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import androidx.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;

public class Client {
    private static Client instance = null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private Client(File cacheDir) {
        Cache cache = new DiskBasedCache(cacheDir, 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
            @Nullable
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    static public synchronized Client getInstance(@Nullable Context ctx) {
        if (instance == null) {
            assert ctx != null;
            File cacheDir = ctx.getCacheDir();
            instance = new Client(cacheDir);
        }
        return instance;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public RequestQueue getQueue() {
        return requestQueue;
    }

}
