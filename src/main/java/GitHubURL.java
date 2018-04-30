import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

/**
 * URL for GitHub API.
 *
 * @author Yaniv Inbar
 */
public class GitHubURL extends GenericUrl {

  @Key
  private String fields;

  public GitHubURL(String encodedUrl) {
    super(encodedUrl);
  }

  /**
   * @return the fields
   */
  public String getFields() {
    return fields;
  }

  /**
   * @param fields the fields to set
   */
  public void setFields(String fields) {
    this.fields = fields;
  }
}