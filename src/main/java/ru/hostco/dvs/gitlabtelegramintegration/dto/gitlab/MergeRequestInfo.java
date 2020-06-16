package ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MergeRequestInfo {

  Long id;
  Long iid;
  @JsonProperty("project_id")
  Long projectId;
  String title;
  String state;
  @JsonProperty("merged_by")
  GitLabUser mergedBy;
  @JsonProperty("closed_by")
  GitLabUser closedBy;
  GitLabUser assignee;
  GitLabUser author;
  @JsonProperty("work_in_progress")
  Boolean workInProgress;
  @JsonProperty("web_url")
  String webUrl;
  @JsonProperty("source_branch")
  String sourceBranch;
}
