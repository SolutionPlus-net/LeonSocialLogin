package am.leon.sociallogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import am.leon.sociallogin.models.FacebookModel;
import am.leon.sociallogin.models.SnapChatModel;
import am.leon.sociallogin.models.TwitterModel;
import am.leon.sociallogin.response.SocialResponse;

public class MainActivity extends AppCompatActivity implements SocialLogin.SocialLoginCallback {

    private SocialLogin socialLogin;
    private AppCompatButton socialLogin_faceBook, socialLogin_twitter, socialLogin_snapChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socialLogin = new SocialLogin(this, this);

        socialLogin_faceBook = findViewById(R.id.socialLogin_faceBook);
        socialLogin_twitter = findViewById(R.id.socialLogin_twitter);
        socialLogin_snapChat = findViewById(R.id.socialLogin_snapChat);

        socialLogin_faceBook.setOnClickListener(v -> socialLogin.facebookLogin());

        socialLogin_twitter.setOnClickListener(v -> socialLogin.twitterLogin());

        socialLogin_snapChat.setOnClickListener(v -> socialLogin.snapChatLogin());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        socialLogin.onResult(requestCode, resultCode, data);
    }

    @Override
    public void socialLoginResponse(SocialResponse<?> social) {
        switch (social.getProviderType()) {
            case TWITTER:
                TwitterModel twitterModel = (TwitterModel) social.getResponse();
                System.out.println(twitterModel.toString());
                break;

            case FACEBOOK:
                FacebookModel facebookModel = (FacebookModel) social.getResponse();
                System.out.println(facebookModel.toString());
                break;

            case SNAPCHAT:
                SnapChatModel snapChatModel = (SnapChatModel) social.getResponse();
                System.out.println(snapChatModel.toString());
                break;
        }
    }
}