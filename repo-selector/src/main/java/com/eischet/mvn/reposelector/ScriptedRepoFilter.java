package com.eischet.mvn.reposelector;

import com.google.auto.service.AutoService;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.metadata.Metadata;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.spi.connector.filter.RemoteRepositoryFilter;
import org.eclipse.aether.spi.connector.filter.RemoteRepositoryFilterSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 This is an experiment, which currently does not work.
 There might be a better way to achieve the same result: https://github.com/cstamas/rrf-demo
 Though the docs are so convoluted, I didn't grok them yet.
 https://maven.apache.org/resolver/remote-repository-filtering.html


 */

@AutoService(RemoteRepositoryFilterSource.class)
public class ScriptedRepoFilter implements RemoteRepositoryFilterSource {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public ScriptedRepoFilter() {
        log.info("ScriptedRepoFilter created");
        System.err.println("XXX: ScriptedRepoFilter created");
    }

    protected static class MyResult implements RemoteRepositoryFilter.Result {

        protected final boolean accepted;
        protected final String reasoning;

        public MyResult(boolean accepted, String reasoning) {
            this.accepted = accepted;
            this.reasoning = reasoning;
        }

        @Override
        public boolean isAccepted() {
            return accepted;
        }

        @Override
        public String reasoning() {
            return reasoning;
        }

    }


    @Override
    public RemoteRepositoryFilter getRemoteRepositoryFilter(final RepositorySystemSession repositorySystemSession) {
        log.info("getRemoteRepositoryFilter");
        System.err.println("XXX: ScriptedRepoFilter used");
        return new RemoteRepositoryFilter() {
            @Override
            public Result acceptArtifact(final RemoteRepository remoteRepository, final Artifact artifact) {
                log.info("accept artifact: {} repo: {}", artifact, remoteRepository.getId());
                System.err.println("XXX: ScriptedRepoFilter artifact");
                return new MyResult(true, "dummy accept artifact");
            }

            @Override
            public Result acceptMetadata(final RemoteRepository remoteRepository, final Metadata metadata) {
                log.info("accept metadata: {} repo: {}", metadata, remoteRepository.getId());
                System.err.println("XXX: ScriptedRepoFilter metadata");
                return new MyResult(true, "dummy accept metadata");
            }
        };
    }
}
