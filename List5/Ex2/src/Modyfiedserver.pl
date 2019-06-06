  use HTTP::Daemon;
  use HTTP::Status;  
  #use IO::File;

  my $d = HTTP::Daemon->new(
           LocalAddr => 'localhost',
           LocalPort => 4322,
       )|| die;
  
  print "Please contact me at: <URL:", $d->url, ">\n";


  while (my $c = $d->accept) {
      while (my $r = $c->get_request) {

              $response = HTTP::Response->new (200 , 'OK');
			  $response->content($r->as_string );
			  $c->send_response($response);
      }
      $c->close;
      undef($c);
}
