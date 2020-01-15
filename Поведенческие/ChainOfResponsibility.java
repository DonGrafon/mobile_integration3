public abstract class AuthenticationProcessor {
 
    public AuthenticationProcessor nextProcessor;
     
    // standard constructors
 
    public abstract boolean isAuthorized(AuthenticationProvider authProvider);
}

public class OAuthProcessor extends AuthenticationProcessor {
 
    public OAuthProcessor(AuthenticationProcessor nextProcessor) {
        super(nextProcessor);
    }
 
    @Override
    public boolean isAuthorized(AuthenticationProvider authProvider) {
        if (authProvider instanceof OAuthTokenProvider) {
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.isAuthorized(authProvider);
        }
         
        return false;
    }
}

public class UsernamePasswordProcessor extends AuthenticationProcessor {
 
    public UsernamePasswordProcessor(AuthenticationProcessor nextProcessor) {
        super(nextProcessor);
    }
 
    @Override
    public boolean isAuthorized(AuthenticationProvider authProvider) {
        if (authProvider instanceof UsernamePasswordProvider) {
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.isAuthorized(authProvider);
        }
        return false;
    }
}

public class ChainOfResponsibilityTest {
 
    private static AuthenticationProcessor getChainOfAuthProcessor() {
        AuthenticationProcessor oAuthProcessor = new OAuthProcessor(null);
        return new UsernamePasswordProcessor(oAuthProcessor);
    }
 
    @Test
    public void givenOAuthProvider_whenCheckingAuthorized_thenSuccess() {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
        assertTrue(authProcessorChain.isAuthorized(new OAuthTokenProvider()));
    }
 
    @Test
    public void givenSamlProvider_whenCheckingAuthorized_thenSuccess() {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
  
        assertFalse(authProcessorChain.isAuthorized(new SamlTokenProvider()));
    }
}