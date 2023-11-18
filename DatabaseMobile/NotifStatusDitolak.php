<?php

define('SERVER_KEY', 'BB2RNbdKUZ890A1KIMX6gxRmxZ-h-7PocY2XLczxSEHU824VnK7XdoVq3_RbKMd0q6zKvjo_x2i3uUDrXsYJXe4');
define('CAMPAIGN_ID', '6935502493132155873');

$data = [
    'message' => [
        'webpush' => [
            'notification' => [
                'title' => 'Nganjuk',
                'body' => 'Gatau gw bang'
            ],
            'fcm_options' => [
                'link' => 'https://yourwebsite.com'
            ]
        ],
        'topic' => 'campaign-' . CAMPAIGN_ID
    ]
];

$headers = [
    'Authorization: key=' . SERVER_KEY,
    'Content-Type: application/json',
];

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, 'https://fcm.googleapis.com/v1/projects/nganjukelok/messages:send');
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));

$result = curl_exec($ch);

if ($result === false) {
    echo 'Curl failed: ' . curl_error($ch);
} else {
    echo 'Message sent successfully!';
}

curl_close($ch);
