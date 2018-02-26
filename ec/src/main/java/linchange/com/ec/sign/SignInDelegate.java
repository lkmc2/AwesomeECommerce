package linchange.com.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import linchange.com.core.delegates.AwesomeDelegate;
import linchange.com.core.net.RestClient;
import linchange.com.core.net.callback.ISuccess;
import linchange.com.core.util.log.AwesomeLogger;
import linchange.com.ec.R;
import linchange.com.ec.R2;

/**
 * Created by lkmc2 on 2018/2/22.
 * 登陆页面Fragment
 */

public class SignInDelegate extends AwesomeDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null; //邮箱输入框

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null; //密码输入框

    private ISignListener mSignListener = null; //登陆注册监听器

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ISignListener) { //activity实现了登陆注册监听器的接口
            mSignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() { //登陆按钮点击事件
        if (checkForm()) { //表单认证通过
            RestClient.builder()
                    .url("http://or6naol85.bkt.clouddn.com/info.json")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            AwesomeLogger.json("USER_PROFILE", response);
                            AwesomeLogger.e(SignUpDelegate.class.getSimpleName(), response);
                            SignHandler.onSignIn(response, mSignListener); //注册，将json数据持久化到本地
                        }
                    })
                    .build()
                    .post();
            AwesomeLogger.e(SignUpDelegate.class.getSimpleName(), "执行完毕");
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() { //微信登陆点击事件

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() { //点击注册链接
        start(new SignUpDelegate()); //启动注册页面
    }

    /**
     * 检查用户输入表单信息是否正确
     * @return 输入信息是否正确
     */
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();


        boolean isPass = true; //是否通过

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码不能少于6位");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
