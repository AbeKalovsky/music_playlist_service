package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.SongModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModelConverter {


    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        List<String> tags = null;
        if (playlist.getTags() != null) {
            tags = new ArrayList<>(playlist.getTags());
        }

        return PlaylistModel.builder()
            .withId(playlist.getId())
            .withName(playlist.getName())
            .withCustomerId(playlist.getCustomerId())
            .withSongCount(playlist.getSongCount())
            .withTags(tags)
            .build();
    }

    public SongModel toSongModel(AlbumTrack albumTrack) {

        return SongModel.builder()
                .withAsin(albumTrack.getAsin())
                .withAlbum(albumTrack.getAlbumName())
                .withTrackNumber(albumTrack.getTrackNumber())
                .withTitle(albumTrack.getSongTitle())
                .build();
    }

    public LinkedList<SongModel> toSongModelList(LinkedList<AlbumTrack> songs) {
        LinkedList<SongModel> songModelList = new LinkedList<>();
        for (AlbumTrack song : songs) {
            SongModel songModel = toSongModel(song);
            songModel.setAsin(song.getAsin());
            songModel.setAlbum(song.getAlbumName());
            songModel.setTitle(song.getSongTitle());
            songModel.setTrackNumber(song.getTrackNumber());
            songModelList.add(songModel);
        }
        return  songModelList;
    }
}
