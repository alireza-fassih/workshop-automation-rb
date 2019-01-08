package ir.fassih.workshop.core.holder;

import ir.fassih.workshop.core.exceptions.AppNotFoundException;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

public class AppHolder {


    private AppHolder() {
        throw new UnsupportedOperationException();
    }

    private static ThreadLocal<AppModel> holder = new ThreadLocal<>();



    @Value
    @Builder
    public static class AppModel {
        private Long id;
        private String title;
    }


    public static AppModel getAppModel() {
        return holder.get();
    }


    public static Long getAppId() {
        return Optional.ofNullable(holder.get())
            .map(AppModel::getId).orElseThrow(AppNotFoundException::new);
    }


    public static void setAppModel(AppModel model) {
        holder.set(model);
    }

}
