package ru.hostco.dvs.gitlabtelegramintegration.service;

import java.util.Collection;
import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab.GitLabUser;
import ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab.MergeRequestInfo;

@FeignClient(value = "gitlab-api", url = "https://git.hostco.ru")
public interface GitLabClient {

  @RequestMapping(value = "/api/v4/merge_requests", method = RequestMethod.GET)
  Collection<MergeRequestInfo> getMergeRequests(
      @NonNull @RequestHeader("Private-Token") String privateToken,
      @RequestParam(value = "scope", defaultValue = "all") String scope);

  @RequestMapping(value = "/api/v4/user", method = RequestMethod.GET)
  GitLabUser getCurrentUserInfo(
      @NonNull @RequestHeader("Private-Token") String privateToken);

  @RequestMapping(value = "/api/v4/projects/{projectId}/merge_requests/{mergeRequestIid}", method = RequestMethod.PUT)
  MergeRequestInfo changeMergeRequestAssignee(
      @NonNull @RequestHeader("Private-Token") String privateToken,
      @NonNull @PathVariable Long projectId,
      @NonNull @PathVariable Long mergeRequestIid,
      @NonNull @RequestParam("assignee_id") Long assigneeId);

  @RequestMapping(value = "/api/v4/projects/{projectId}/members", method = RequestMethod.GET)
  Collection<GitLabUser> getProjectMembers(
      @NonNull @RequestHeader("Private-Token") String privateToken,
      @NonNull @PathVariable Long projectId);
}
