package ir.fassih.workshop.core.holder;

import lombok.Builder;
import lombok.Value;

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


    public static void setAppModel(AppModel model) {
        holder.set(model);
    }

}
