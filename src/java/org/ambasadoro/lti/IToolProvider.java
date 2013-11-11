package org.ambasadoro.lti;

public interface IToolProvider {

    public boolean hasValidSignature(String url, String sharedSecret) throws Exception;

}
