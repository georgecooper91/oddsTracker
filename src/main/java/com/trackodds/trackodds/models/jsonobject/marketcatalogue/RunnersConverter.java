//package com.trackodds.trackodds.models.jsonobject.marketcatalogue;
//
//
//import com.trackodds.trackodds.resource.GetMatchInfoImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class RunnersConverter implements Converter<String, Runners> {
//
//    GetMatchInfoImp getMatchInfoImp;
//
//    @Autowired
//    public RunnersConverter(GetMatchInfoImp getMatchInfoImp) {
//        this.getMatchInfoImp = getMatchInfoImp;
//    }
//
//    @Override
//    public Runners convert(String source) {
//        System.out.println("Trying to convert id=" + source + " into a runner");
//
//        long selectionId = Long.parseLong(source);
//        List<Runners> runnersList = Arrays.asList(
//                new Runners(123)
//        );
//        return null;
//    }
//}
